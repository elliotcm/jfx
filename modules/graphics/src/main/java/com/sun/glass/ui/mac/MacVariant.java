/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.glass.ui.mac;

import java.util.Arrays;

import javafx.geometry.Bounds;

class MacVariant {
    final static int NSArray_id = 1;
    final static int NSArray_NSString = 2;
    final static int NSArray_int = 18;
    final static int NSArray_range = 19;
    final static int NSAttributedString = 3;
    final static int NSData = 4;
    final static int NSDate = 5;
    final static int NSDictionary = 6;
    final static int NSNumber_Boolean = 7;
    final static int NSNumber_Int = 8;
    final static int NSNumber_Float = 9;
    final static int NSNumber_Double = 10;
    final static int NSString = 11;
    final static int NSURL = 12;
    final static int NSValue_point = 13;
    final static int NSValue_size = 14;
    final static int NSValue_rectangle = 15;
    final static int NSValue_range = 15;
    final static int NSObject = 17; /* id */

    int type;
    long[] longArray;
    int[] intArray;
    String[] stringArray;
    float float1;
    float float2;
    int int1;
    int int2;
    String string;
    long long1;
    double double1;

    static MacVariant createNSArray(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSArray_id;
        variant.longArray = (long[])result;
        return variant;
    }

    static MacVariant createNSObject(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSObject;
        variant.long1 = (Long)result;
        return variant;
    }

    static MacVariant createNSString(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSString;
        variant.string = (String)result;
        return variant;
    }

    static MacVariant createNSAttributedString(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSAttributedString;
        variant.string = (String)result;
        return variant;
    }

    static MacVariant createNSValueForSize(Object result) {
        Bounds bounds = (Bounds)result;
        MacVariant variant = new MacVariant();
        variant.type = NSValue_size;
        variant.float1 = (float)bounds.getWidth();
        variant.float2 = (float)bounds.getHeight();
        return variant;
    }

    static MacVariant createNSValueForPoint(Object result) {
        Bounds bounds = (Bounds)result;
        MacVariant variant = new MacVariant();
        variant.type = NSValue_point;
        variant.float1 = (float)bounds.getMinX();
        variant.float2 = (float)bounds.getMinY();
        return variant;
    }

    static MacVariant createNSValueForRange(Object result) {
        int[] range = (int[])result;
        MacVariant variant = new MacVariant();
        variant.type = NSValue_range;
        variant.int1 = range[0];
        variant.int2 = range[1];
        return variant;
    }

    static MacVariant createNSNumberForBoolean(Object result) {
        Boolean value = (Boolean)result;
        MacVariant variant = new MacVariant();
        variant.type = NSNumber_Boolean;
        variant.int1 = value ? 1 : 0;
        return variant;
    }

    static MacVariant createNSNumberForDouble(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSNumber_Double;
        variant.double1 = (Double)result;
        return variant;
    }

    static MacVariant createNSNumberForInt(Object result) {
        MacVariant variant = new MacVariant();
        variant.type = NSNumber_Int;
        variant.int1 = (Integer)result;
        return variant;
    }

    Object getValue() {
        switch(type) {
        case NSNumber_Boolean: return int1 != 0;
        case NSNumber_Int: return int1;
        case NSNumber_Double: return double1;
        case NSArray_id: return longArray;
        case NSArray_int: return intArray;
        case NSValue_range: return new int[] {int1, int2};
        //TODO REST
        }
        return null;
    }

    @Override
    public String toString() {
        Object v = getValue();
        switch(type) {
        case NSArray_id: v = Arrays.toString((long[])v); break;
        case NSArray_int: v = Arrays.toString((int[])v); break;
        case NSValue_range: v = Arrays.toString((int[])v); break;
        }
        return "MacVariant type: " + type + " value " + v;
    }
}