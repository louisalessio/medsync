# DTO Pattern and MapStruct

## Status
Accepted

## Context
To prevent exposing the database schema directly to the client

## Decision
We use DTOs (Data Transfer Objects) for all API requests and responses. We use MapStruct for compile-time safe object mapping.

## Consequences
Better security (no mass-assignment) and decoupling of API and DB models.