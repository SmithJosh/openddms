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
package org.codice.ddms.v2.resource

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes

data class Title(
    val securityAttributes: SecurityAttributes,
    val value: String
) {
    init {
        with(securityAttributes) {
            require(classification != Classification.NO_CLASSIFICATION) {
                "ddms:title and ddms:subtitle must contain a ism:classification"
            }
            require(ownerProducer.isNotEmpty() && ownerProducer.any { it.isNotBlank() }) {
                "ddms:title and ddms:subtitle must contain at least one ism:ownerProducer"
            }
        }
    }
}
