# **project-coders-inc**

## Authors
* Benjamin Bichel
* Darren Holden
* Gabrielle Hubert
* Andrew Vicencio

## Deliverables
* DimensionalSpace.java
* Point.java
* TestEnvironment.java
* UML file
* javadoc
* User Manual


## Changes Since Last Milestone
* Created Point class to hold data from a dataset entry
	* Holds raw and standardized point
	* Provides getters and setters for all included info
	* Provides a standardization method to standardize the point
	* Holds the "goal" value for the point
* Created DimensionalSpace class to hold the points and perform the kNN algorithm
	* Holds a set of points that describe the dataset
	* Calculates the mean value of each coordinate that make up the points
	* Performs the kNN algorithm using a given point and a number of nearest neighbours
		* Finds the nearest k neighbours and sets the goal value to the average of the neighbours' goal values
* Created TestEnvironment class to create test scenarios by which the kNN algorithm is evaluated

## Known Issues
* The standard deviation should be calculated in DimensionalSpace, not Point
* The kNN algorithm should have its own class
	* This will lend itself to the Strategy Design Pattern
	* Will also allow for more flexibility
* Program is not very user-friendly at the moment
	* Due to hard-coding
* Coordinate values for points are not very flexible at the moment
	* Due to only accepting Integers for the values in the key-value pairs
		* Perhaps a type parameter could be used for more flexibility

## Future Steps
* Redesign some aspects to improve cohesion and create looser coupling
* Make the program more user-friendly
* Create GUI
* Implement unit test
* Retrieving user input as data
* Increase flexibility (to be clarified at a later date)
* Implement save/load functionality

## To Do List






