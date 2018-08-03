/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddms.gml.v3.validation

import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PolygonTest {
    private val exterior = LinearRing(listOf(Position(listOf(0.0, 0.0)),
            Position(listOf(1.0, 1.0)),
            Position(listOf(2.0, 2.0)),
            Position(listOf(3.0, 3.0))))
    private val srsAttributes = SrsAttributes("wgs84")

    @Test(expected = IllegalArgumentException::class)
    fun `a Polygon without an id is invalid`() {
        Polygon("", exterior, srsAttributes)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Polygon without an srsName is invalid`() {
        Polygon("id", exterior, SrsAttributes())
    }

    @Test
    fun `a Polygon with an id and srsName is valid`() {
        Polygon("id", exterior, srsAttributes)
    }

    @Test
    fun `a valid Polygon creates correct xml`() {
        val xml = "<gml:Polygon gml:id=\"id\" srsName=\"wgs84\" xmlns:gml=\"http://www.opengis.net/gml\">" +
                "<gml:exterior><gml:LinearRing xmlns:gml=\"http://www.opengis.net/gml\">" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0 0.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">1.0 1.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">2.0 2.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">3.0 3.0</gml:pos>" +
                "</gml:LinearRing></gml:exterior></gml:Polygon>"
        val polygon = Polygon("id", exterior, srsAttributes)

        assertThat(polygon.toString(), equalTo(xml))
    }
}
