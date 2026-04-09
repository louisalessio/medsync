# Use Hibernate (Spring Data JPA) as ORM

## Status
Accepted

## Context
We need a way to persist our domain objects (Patient) into a relational database (H2/PostgreSQL). 
The choice was between Hibernate (standard ORM) and MyBatis (SQL Mapper).

## Decision
We decided to use Hibernate through Spring Data JPA.

## Rationale
1. **Productivity:** Hibernate handles basic CRUD operations automatically, allowing us to focus on medical logic.
2. **Standardization:** It follows the JPA specification, which is the industry standard in Switzerland.
3. **Database Abstraction:** It allows us to switch from H2 (development) to PostgreSQL (production) with minimal config changes.

## Consequences
- **Pro:** Faster development and cleaner service layer.
- **Con:** Less control over highly optimized raw SQL (not a priority for this stage of the project).