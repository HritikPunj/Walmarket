# Iteration2  Worksheet


## Paying off Technical Debt
- To start off iteration 2 our group prioritized paying off technical debt incurred during iteration 1. Some of the most obvious of these were the state of coupling between our layers. Our initial architechture focused on ensuring the product was functional and that operations were confined to their respective layers, but there was always an understanding that refactoring would be needed to keep the growing codebase maintainable. The link below showcases our choice to convert the parameters of many method calls into single data packet classes which could be modified as the needs of the app change. This allows us to keep things like method signatures consistent through the addition of new features and therefore minamize conflicts between changes for differing features.
  - [Link to commit.](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/0.2.1/app/src/main/java/comp3350/Innovator2/presentation/Browser.java#L223)

<br>

- Keeping with the theme of the point above, another major area of technical debt was within our stub database implementation. In hindsight, the stub should have been implemented in a modular fashion so that when the time came to add our real database the process would be smooth. Instead, we incurred a fair bit of technical debt during our iteration 1 stub setup which required cleaning during iteration 2. The link below showcases our new approach, using a Services class and a series of interfaces to keep the underlying data layer abstract.
  - [Link to commit.](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/0.2.1/app/src/main/java/comp3350/Innovator2/application/Services.java#L21)

## SOLID

- Type of SOLID violation - Single Responsibility Principle.
- Description: View adapter class is also responsible for opening prompts.
- Link to [Issue](https://code.cs.umanitoba.ca/comp3350-winter2024/lethalcompany-a01-13/-/issues/156) created

## Retrospective
The retrospective has made us define clearer roles within our team. With Sydney working on the design of the UI, Matt working on UI functionality, Daniyal working in the logic layer, Hritik working in the data layer, and Cody smoothing the seams between the layers and helping with debugging. We were also more conscious of the 
structure of the code and instead of coding to make the current iteration work, we were coding to make also make future iterations easier. An example of this is the refactoring that was done in the presentation layer to simplify what was already done and using an abstract class ItemView for similar UI elements in the item browser 
view and the cart view. Changes to the presentation layer can be found at [link](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/merge_requests/58/diffs#20a23a590af0253035135e5ccea29480200ab233) 

## Design Patterns

- We used Factory Method design pattern to create credit cards. In our project, credit card is an abstract class and Visa card and Mastercard are subclassed of this class. We can create credit cards using the create() method in CreditCardFactory class.
- [Link](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/0.2.1/app/src/main/java/comp3350/Innovator2/logic/CreditCardFactory.java#L7) to commit.

## Iteration 1 Feedback Fixes

- No specific issues opened by a grader for iteration 1 so we will mention another instance where technical debt was paid off. 
- The [credit] Card Validator was initially returning true or false based on validity which was not helpful to either the developer or the user and needed refactoring. This can be classified to require Class-implementation refactoring as it needed to essentially redo and move methods for the validation. The debt was repaid by implementing 
specific errors for the 3 main invalid payment options (invalid card number, invalid CVV and invalid expiry date). The validateCard() method runs 3 if-statements which test each possible failure, if any result in false then an appropriate exception is thrown which is caught and displayed by the UI Layer; the card is valid if no exceptions were thrown.
- [Link](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/0.2.1/app/src/main/java/comp3350/Innovator2/logic/CardValidator.java#L29) to commit.

