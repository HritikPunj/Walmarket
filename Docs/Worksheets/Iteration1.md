# Iteration1  Worksheet

## Adding a Feature
The [Item Browser](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/2) feature was added to our project as it is the essence of an online shopping application. 
We want our application to be centered around the items that will be uploaded to the app that will be available for users to purchase. 
The Item Browser feature allow users to look at items available for purchase, search for items, and filter items by category.

The user stories for this feature include:
- [Organize Items](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/10) – Allows users to filter items when they are browsing
- [See Items](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/11) – Allows users to browse through all items available to purchase
- [Search Items](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/12) – Allows users to use a search bar to find items they are looking for
- [Tests](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/tree/feature_itembrowser/app/src/test/java/comp3350/Innovator2?ref_type=heads) associated with Item Browser feature 
- [Merge requests](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/merge_requests?scope=all&state=merged&target_branch=feature_itembrowser) associated with the Item Browser feature branch
- [Commit](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/commit/8cc2b9048816721c1aa8e5cd264237fadee0562d) for Item Browser feature branch (note – this feature has not yet been closed, one of the user stories associated with this feature has been pushed to iteration 2)

## Branching

- Our branching strategy employed was Git Flow and its details, as well as our own additions/modifications are detailed [here](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/Docs/Branching.md).

- A screenshot showcasing the strategy in action is located [here](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/Docs/Branching_Diagram.png).

## SOLID 

Group 13 Violated the Single-Responsibility Principle when they created many prompts all as unique methods in their ShoppingListFragment.java file. This code doesn’t need to be all in one class, in fact, the amount of duplicate code between the methods could benefit from an abstract class to extend from.
- Link to [Issue](https://code.cs.umanitoba.ca/comp3350-winter2024/lethalcompany-a01-13/-/issues/83) created

## Agile Planning
There was a medium priority user story that we opted to push to Iteration 2. It was titled [Organize Items](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/10) and was in hopes to create a filtering system in our searches to facilitate categorization of items. The time estimates for fetching data from the database took much longer than anticipated, 
propagating its way to the following part of our program—the organization of items. This also pushed the dev task of [Create Filter/Organization UI](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/issues/26) to the second iteration. 

## Retrospective
- Link to [Retrospective](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/Docs/Retrospective/Iteration1_Retrospective.md)
- **Proposed Solutions:**
  - **Setting Deadlines:** We will set a deadline for ourselves, a couple of days before the actual deadline. NO more new features/user stories to be added past that deadline.
   We will use the remaining time to comprehensively test and fix bugs, which we encounter.
  - **Thorough Discussions:** We will try to be more specific with design decisions by having discussions about the details of implementation and refactor code accordingly.
  - **Dividing work:** Now we have more idea about the time requirements, and we can delegate work between ourselves in a better way. If something takes longer than expected
   we will update the time requirements accordingly.

## Retrospective Per Member

- ### Cody Gordon
  - In this first iteration I believe I did a good job at facilitating discussions during our meetings, as well as managing/organizing the repository.
  - The best code I implemented would be the [QueryRunner](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/app/src/main/java/comp3350/Innovator2/data/QueryRunner.java) which simulates SQL query results for our stub database and retrieves sorted data (DSOs).  


- ### Matthew Boychuk
  - I believe that what I did best in the first iteration was working well under pressure, connecting everything and troubleshooting before the deadline. Due to the pressure, much of the code I wrote was messy and will be refactored for the second iteration.
  - My best code would be the [BrowserHelper](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/app/src/main/java/comp3350/Innovator2/presentation/BrowserHelper.java?ref_type=heads) that manages the details of the item browser, such as handling inputs from the UI to switch pages and calling necessary methods to display items.  


- ### Daniyal Hasnain 
  - I remained punctual with the code I was assigned. It was a simple piece of logic as this iteration was more UI-heavy, but having the connection between the data and presentation layers was imperative to the entire project. As the logic was minimal in this iteration, I ended up contributing less to the group whilst leaving other members with heavier loads.
  - My [best code](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/app/src/main/java/comp3350/Innovator2/logic/CardValidator.java?ref_type=heads ), though straightforward, was for the verification of payment. Due to the shortage of time, I was only able to get a simple check in based on card number and type but ensured to test a plethora of card combinations. 
    A dev task to expand and refactor the file have already been created for iteration 2.  


- ### Sydney Pratt
  - I believe what I did best in iteration 1 was creating the main view for the app and providing team members with what was necessary to get the view into working order.
  - I believe my best code is the [item template](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/blob/main/app/src/main/res/layout/item_view.xml?ref_type=heads) that is inserted into a recycler view in the main activity that allows several items to be displayed.  


- ### Hritik Punj
  - I think the best I did in iteration 1 was coordinating meetings and finishing my work on time. My main task in this iteration was creating DSO’s and planning. My work had to be done before as other parts of the application were dependent on that.
  - Although it was not a difficult task, but I believe my best code is creating the [Domain Specific Objects](https://code.cs.umanitoba.ca/comp3350-winter2024/1nnovator2-a01-12/-/commit/5901d1809974e0e530a4116e9f21e2ed78d5dde6) (all of them).
  - I realized I do made a mistake, as I forgot to remove dead code. I was thinking about the removing the bits I do not need for Iteration 1. I commented out the code for testing in the development branch but forgot to remove it before merging into main.
    I have already starting working on a devTask to create a database and refactor Domain Specific Objects for full functionality for next Iteration.
