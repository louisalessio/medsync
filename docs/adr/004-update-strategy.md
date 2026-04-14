# Update Strategy (@MappingTarget)

## Status
Accepted

## Context
Updating entities without replacing the whole object or losing metadata

## Decision
Use MapStruct's @MappingTarget to update existing JPA entities with DTO data

## Consequences
Entity identity (ID) and system fields are preserved; only allowed fields are updated
