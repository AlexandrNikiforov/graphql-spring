## Syntax notes

* `id: ID!` - ! means not null

## Query notes

```
{
  schedule(coach: PHILIP) {
    id
    coach
    startsAt
    endsAt
    difficulty
    }
}
```