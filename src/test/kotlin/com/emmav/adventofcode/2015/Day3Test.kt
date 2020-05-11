package com.emmav.adventofcode.`2015`

import junit.framework.Assert.assertEquals
import org.junit.Test

class Day3Test {

    @Test fun example1() {
        assertEquals(2, housesThatReceiveAtLeastOnePresent(">"))
    }

    @Test fun example2() {
        assertEquals(4, housesThatReceiveAtLeastOnePresent("^>v<"))
    }

    @Test fun example3() {
        assertEquals(2, housesThatReceiveAtLeastOnePresent("^v^v^v^v^v"))
    }
}