# LeetCode Solutions

A well-organized Maven-based Java project for solving and testing LeetCode problems.

## Project Structure

```
leetcode/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/leetcode/
│   │           ├── easy/
│   │           ├── medium/
│   │           └── hard/
│   ├── test/
│   │   └── java/
│   │       └── com/leetcode/
│   │           ├── easy/
│   │           ├── medium/
│   │           └── hard/
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher

## Setup

1. **Clone or navigate to the project:**
   ```bash
   cd /path/to/leetcode
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

## Building and Testing

### Compile the project
```bash
mvn clean compile
```

### Run all tests
```bash
mvn test
```

### Run a specific test class
```bash
mvn test -Dtest=YourTestClassName
```

### Run a specific test method
```bash
mvn test -Dtest=YourTestClassName#testMethodName
```

## Adding a New Solution

1. **Create a solution class** in the appropriate difficulty folder:
   ```
   src/main/java/com/leetcode/easy/TwoSum.java
   ```

2. **Create a test class** with the same name:
   ```
   src/test/java/com/leetcode/easy/TwoSumTest.java
   ```

### Example Solution

**TwoSum.java:**
```java
package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

**TwoSumTest.java:**
```java
package com.leetcode.easy;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TwoSumTest {
    private final TwoSum solution = new TwoSum();

    @Test
    public void testTwoSum() {
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertThat(result).containsExactly(0, 1);
    }

    @Test
    public void testTwoSumMultiplePairs() {
        int[] result = solution.twoSum(new int[]{3, 2, 4}, 6);
        assertThat(result).containsExactly(1, 2);
    }
}
```

## Dependencies

- **JUnit 5** - Testing framework
- **AssertJ** - Fluent assertions library

## IDE Integration

### IntelliJ IDEA
- Maven support is built-in
- Right-click test file → Run to execute tests

### VS Code
- Install "Extension Pack for Java"
- Tests will appear in the Test Explorer

### Eclipse
- Maven support is built-in
- Use Maven → Run As → Maven Build

## Tips

1. **Organize by difficulty**: Keep easy, medium, and hard problems in separate packages
2. **Write tests first**: Practice TDD methodology
3. **Use meaningful names**: Name files after the problem name
4. **Add comments**: Include LeetCode problem URL as a comment
5. **Version control**: Use `.gitignore` to exclude target/ and IDE files

## Useful Commands

- `mvn clean` - Remove build artifacts
- `mvn compile` - Compile source code
- `mvn test` - Run all tests
- `mvn package` - Create JAR file
- `mvn install` - Install JAR to local repository

## License

MIT License - Feel free to use this for personal learning

## Notes

- Each solution should be self-contained and have corresponding tests
- Follow Java naming conventions and best practices
- Add problem descriptions and approach comments to your code
- Time and space complexity analysis helps with learning