# OODP-CA1
 Repository for Object Oriented with Design Principles CA 1
 
 The two Creational Patterns that I decided to use on this CA were the Singleton and Builder Patterns.

Since the Singleton Pattern involves a single class which is responsible to create an object while making sure that only a single object gets created, it was a perfect solution to my DataSource class, which has a single object that can be accessed directly without the need of an instantiation. 

The Builder Pattern lets you construct complex objects step by step, and allows you to produce different types and representations of an object using the same construction code. It also allows us to isolate the construction code from the business logic of the product.

Although I didn't need to build different types of Countries on this project, this pattern was perfect to meet the requirement of passing and receiving data only in the form of objects, which were instances of the Country class (Builder).
