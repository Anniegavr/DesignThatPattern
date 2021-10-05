# DesignThatPattern
Laboratory Work on Design Patterns
Lab #1: Creational DP - Anastasia Gavrilita
/*Check the older repo - currently named "demo" to see earlier commits. The repo was switched due to some technical issues

Design patterns are typical solutions to commonly occurring problems in software design. They are like pre-made blueprints that you can customize to solve a recurring design problem in the code.
The most common classification of patterns in programming is: creational, structural and behavioral patterns. Creational patterns provide various object creation mechanisms, which increase flexibility and reuse of existing code.

The code above presents an example of implementing an abstract factory (IRecommFactory), implemented by 2 concrete factory methods (BodyActivityRecommFactory & WatchRecommFactory) - all stored in the Factories package.
What's more, the client and his saved preferences are instantiated as singletons - see the Client and the Models packages. A switch is used for each of the concrete factories' implementation.

The project mocks up an app that suggests activities based on 2 main criteria: the type of activity (sporty or not & the intensity o concentratrion required). Sport-related recommendations - for hiking, running & walking w/h a friend - will also contain filters for places; watching recommendations will be filtered based on genres. More filters might be added later on. The datasets for watching recommendations are archived and stored inside the Models package.

Check the UML diagram below for a better perspective on the project's structure.

![image](https://user-images.githubusercontent.com/56108881/135888762-a2457dc8-5a55-4379-b9b5-4f25a9769d60.png)

Although the used patterns are quite common and trivial, it's what was needed for the specific task they do. It's important to understand which pattern is necessarry for a specific task.
