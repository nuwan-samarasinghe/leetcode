# Contributing to LeetCode Solutions

## Guidelines

1. **One problem per class** - Each LeetCode problem should have its own class
2. **Follow the naming convention** - Use the problem name in PascalCase
3. **Include problem details** - Add the LeetCode URL and problem description as a comment
4. **Write comprehensive tests** - Cover edge cases and various scenarios
5. **Include complexity analysis** - Document time and space complexity
6. **Keep code clean** - Follow Java naming conventions and best practices

## File Structure

```
src/main/java/com/leetcode/{difficulty}/{ProblemName}.java
src/test/java/com/leetcode/{difficulty}/{ProblemName}Test.java
```

Example:
```
src/main/java/com/leetcode/easy/TwoSum.java
src/test/java/com/leetcode/easy/TwoSumTest.java
```

## Code Template

```java
package com.leetcode.{difficulty};

/**
 * LeetCode Problem {ID}: {Problem Name}
 * https://leetcode.com/problems/{problem-slug}/
 *
 * {Problem Description}
 *
 * Time Complexity: O(...)
 * Space Complexity: O(...)
 */
public class {ProblemName} {
    // Your solution here
}
```

## Test Template

```java
package com.leetcode.{difficulty};

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class {ProblemName}Test {
    private final {ProblemName} solution = new {ProblemName}();

    @Test
    public void testBasicCase() {
        // Test basic scenario
    }

    @Test
    public void testEdgeCase() {
        // Test edge cases
    }
}
```

## Running Tests

```bash
# Run all tests
mvn test

# Run tests for a specific class
mvn test -Dtest=TwoSumTest

# Run a specific test method
mvn test -Dtest=TwoSumTest#testBasicCase
```

## Before Committing

- [ ] Code compiles without warnings
- [ ] All tests pass
- [ ] Code follows Java conventions
- [ ] Comments are clear and accurate
- [ ] Complexity analysis is documented
