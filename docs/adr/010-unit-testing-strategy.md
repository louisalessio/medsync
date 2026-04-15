# ADR 010: Unit Testing Strategy with Mockito

## Status
Accepted

## Context
We need to ensure business logic reliability without depending on external systems like databases, which makes tests slow and flaky.

## Decision
We adopted **Unit Testing** for the Service Layer using:
- **JUnit 5** as the testing framework.
- **Mockito** for dependency mocking (Repository and Mappers).
- **AssertJ** for fluent and readable assertions.
- **Given/When/Then** pattern for test structure.

## Consequences
- Fast execution (sub-second tests).
- Total isolation of business logic.
- High confidence during refactoring: if we change the code, the tests will immediately catch regressions.