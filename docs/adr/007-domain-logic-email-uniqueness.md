# Domain Logic - Email Uniqueness

## Status
Accepted

## Context
A patient's email must be unique

## Decision
Implement a Repository check (existsByEmail) in the Service layer before saving new patients

## Consequences
Prevents data corruption