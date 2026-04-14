# Logging Standards (SLF4J)

## Status
Accepted

## Context
Need for traceability and debugging in production

## Decision
We use SLF4J via Lombok's @Slf4j. We enforce structured logging with placeholders {} instead of string concatenation

## Consequences
High-performance logging and easier integration with external log aggregators