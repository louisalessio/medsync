# Strict Null Safety Policy

## Status
Accepted

## Context
Reducing NullPointerExceptions (NPE) to zero

## Decision
We use @Nonnull for method parameters/returns and Objects.requireNonNull() as a runtime guard for repository operations

## Consequences
The compiler and IDE help catch potential null bugs before they reach production