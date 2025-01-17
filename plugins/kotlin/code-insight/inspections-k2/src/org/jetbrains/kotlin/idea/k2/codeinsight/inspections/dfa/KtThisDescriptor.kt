// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.kotlin.idea.k2.codeinsight.inspections.dfa

import com.intellij.codeInspection.dataFlow.DfaNullability
import com.intellij.codeInspection.dataFlow.types.DfReferenceType
import com.intellij.codeInspection.dataFlow.types.DfType
import com.intellij.codeInspection.dataFlow.value.DfaVariableValue
import com.intellij.codeInspection.dataFlow.value.VariableDescriptor

class KtThisDescriptor(private val dfType : DfType) : VariableDescriptor  {
    override fun isStable(): Boolean {
        return true
    }

    override fun isImplicitReadPossible(): Boolean {
        return true
    }

    override fun getDfType(qualifier: DfaVariableValue?): DfType = dfType

    override fun equals(other: Any?): Boolean = other is KtThisDescriptor && other.dfType == dfType

    override fun hashCode(): Int = dfType.hashCode()

    override fun toString(): String {
        if (dfType is DfReferenceType) {
            return "${dfType.constraint}${if(dfType.nullability == DfaNullability.NULLABLE) "?" else ""}.this"
        }
        return "$dfType.this"
    }
}