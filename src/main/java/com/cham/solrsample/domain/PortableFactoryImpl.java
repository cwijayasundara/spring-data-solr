package com.cham.solrsample.domain;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class PortableFactoryImpl implements PortableFactory {

    public static final int BOOK_CLASS_ID = 1;

    public static final int FACTORY_ID = 1;

    public Portable create(int classId) {
        switch (classId) {
            case BOOK_CLASS_ID:
                return new HazelcastBook();
            default:
                return null;
        }
    }
}
