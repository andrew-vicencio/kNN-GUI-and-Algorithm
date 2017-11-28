# **project-coders-inc**

## Authors
* Benjamin Bichel
* Darren Holden
* Gabrielle Hubert
* Andrew Vicencio

## Deliverables
* ButtonAddFeaturesController.java
* ButtonMenuController.java
* Cell.java
* CellComposite.java
* CellSimple.java
* ChebyshevKNN.java
* DimensionalSpace.java
* DistanceAlg.java
* DoneButtonController.java
* EuclideanKNN.java
* FeaturePanelComplex.java
* FeaturePanelComplexController.java
* FeaturePanelController.java
* FeaturePanelSimple.java
* FeaturePanelSimpleController.java
* KNN.java
* MainController.java
* ManhattanKNN.java
* MenuController.java
* MinkowskiKNN.java
* MyTests.java
* NumericalDistance.java
* Point.java
* PromptValueFrame.java
* PromptValueFrameController.java
* StringDistance.java
* TestCaseFrame.java
* TestCaseFrameController.java
* TestEnvironment.java
* ValueInputController.java
* ValuePromptFrameController.java
* ValueTestFrameController.java
* View.java
* UML file
* javadoc
* User Manual


## Changes Since Last Milestone
* Added in new KNN distance metrics on top of Euclidean
	* Including
		* Chebyshev
		* Manhattan
		* Minkowski
* Renamed CompositeCell and SimpleCell to CellComposite and CellSimple respectively
* Refactored DimensionalSpace.findKNN to accommodate additional distance metrics and delegate
* Added scrollable panes
* Made the interface much more user friendly
	* Keyboard shortcuts
	* Better formatting
	* More intuitive and easier to read
* Major refactoring of the GUI classes to reduce coupling
* Added GUI support for users to choose new distance metrics
* Allowed blank values to be added, rather than giving an error frame to the user

## Known Issues

## Future Steps
* Add Save/Load functionality
* Increase flexibility

