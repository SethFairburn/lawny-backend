package com.lawny.backend;

public enum ApplicationStatus {
    UPCOMING,
    MISSED,
    APPLIED
}

/*What is an enum?

An enum is a Java type where we define a fixed set of allowed values. Can only be one of those three.

And importantly, we're not adding ApplicationStatus as a database field. We're going to calculate one whenever Lawny needs it.

*/