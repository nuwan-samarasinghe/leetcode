package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
class Edge {

    private String destination;
    private int travelTime;
}

public class ShortestPathInRoad {

    public int shortestTravelTime(String[][] roads, String source, String destination) {

        Map<String, List<Edge>> graph = new HashMap<>();
        for (String[] road : roads) {
            String from = road[0];
            String to = road[1];
            Integer travelTime = Integer.valueOf(road[2]);
            if (graph.containsKey(from)) {
                graph.get(from).add(Edge.builder().destination(to).travelTime(travelTime).build());
            } else {
                List<Edge> edges = new ArrayList<>();
                edges.add(Edge.builder().destination(to).travelTime(travelTime).build());
                graph.put(from, edges);
            }
        }

        Map<String, Integer> shortestTimeMap = new HashMap<>();
        shortestTimeMap.put(source, 0);

        PriorityQueue<String[]> travelOrder = new PriorityQueue<>((a, b) -> Integer.compare(Integer.valueOf(a[1]), Integer.valueOf(b[1])));
        travelOrder.offer(new String[]{source, "0"});

        while (!travelOrder.isEmpty()) {
            String des[] = travelOrder.poll();
            String city = des[0];
            Integer time = Integer.valueOf(des[1]);

            if (city.equals(destination)) {
                return time;
            }

            for (Edge nextDestination : graph.getOrDefault(city, new ArrayList<>())) {
                Integer nextTime = time + nextDestination.getTravelTime();
                if (nextTime < shortestTimeMap.getOrDefault(nextDestination.getDestination(), Integer.MAX_VALUE)) {
                    shortestTimeMap.put(nextDestination.getDestination(), nextTime);
                    travelOrder.offer(new String[]{nextDestination.getDestination(), String.valueOf(nextTime)});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[][] roads = {
            {"A", "B", "1"},
            {"B", "C", "1"},
            {"C", "D", "1"},
            {"D", "E", "1"},
            {"A", "E", "10"}
        };

        String source = "A";
        String destination = "E";

        ShortestPathInRoad solution = new ShortestPathInRoad();
        int result = solution.shortestTravelTime(roads, source, destination);

        System.out.println(result);
    }

}
