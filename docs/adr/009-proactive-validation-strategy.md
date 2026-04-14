# ADR 009: Proactive Data Validation Strategy

## Status
Accepted

## Context
Invalid data entry (e.g., malformed emails, future birth dates) should be caught before reaching the Service layer to ensure data integrity and reduce unnecessary database load.

## Decision
We implemented **Jakarta Bean Validation** constraints within our DTOs:
- `@NotBlank` and `@Email` for identity fields.
- `@Past` for birth dates to ensure logical consistency.
- `@Pattern` with E.164 compliant regex for international phone numbers.
- Used `@Valid` in the Controller to trigger validation immediately upon request entry.

## Consequences
- "Fail Fast" approach: invalid requests are rejected at the edge.
- Automated error reporting through the `GlobalExceptionHandler`.
- Cleaner Service layer (it doesn't need to manually check basic formats).