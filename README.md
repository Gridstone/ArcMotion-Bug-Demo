This demo app is made to show the inconsistency between Android developers' docs on how `ArcMotion` works and its actual implementation.

It specifically illustrates the issue in case the vertical distance between a start point and an end point is greater than their horizontal distance.
According to the docs, in such case the generated arc should be on an imaginary circle whose centre point is horizontally aligned with the end point. However, the actually generated curve has its circle's centre point horizontally aligned to the start point instead.

By looking into the `ArcMotion`'s source code, we believe the description matching the implementation would be: "If the horizontal distance between the points is less than the vertical distance, then the circle's center point will be horizontally aligned with either the start point or the end point, whichever ensures the curve to follow the anticlockwise direction. If the vertical distance is less than the horizontal distance then the circle's centre point will be vertically aligned with either the start point or the end point, whichever ensures the curve to follow the anticlockwise direction."
