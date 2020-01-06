# kotlin-demo

Small app made with Google code lab to show examples of architecture best practices.
Functionality and UX are limited, as code structure is the focus for this demo.

### Overview
- The app displays a list of Characters each with six Abilities.
- An Ability has a Type, a Score, and a Modifier. The Modifier is derived from the Score.
- If the Character table is empty when the app launches, two Characters are created.
- Users may create new Characters by tapping the Floating Action Button in the bottom right of the main page.
- A name must be provided for new characters. Default scores are provided for Abilities in the event input is lacking.
- No validation is done on provided input.
- Tapping on a Character in the list displays a new page showing the Ability modifiers and scores.
