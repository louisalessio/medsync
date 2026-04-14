# Soft Delete Strategy

## Status
Accepted

## Context
In medical systems data is rarely physically deleted for auditing

## Decision
We use Hibernate 6's native @SoftDelete annotation

## Consequences
Deleted records are automatically filtered out from queries and kept in the DB with a logical flag, managed entirely by the framework