package com.cham.solrsample.domain;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Log
public class HazelcastBook  implements Serializable, VersionedPortable {

    private String bookId;
    private String bookName;
    private String level;
    private String owner;
    private String ownerId;

    @Override
    public int getClassId() {
        return PortableFactoryImpl.BOOK_CLASS_ID;    }

    @Override
    public int getFactoryId() {
        return PortableFactoryImpl.FACTORY_ID;
    }

    @Override
    public int getClassVersion() {
        return 1;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        System.out.println("Serialize...");
        writer.writeUTF("bookId", bookId);
        writer.writeUTF("bookName", bookName);
        writer.writeUTF("level", level);
        writer.writeUTF("owner", owner);
        writer.writeUTF("ownerId", ownerId);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize");
        this.bookId = reader.readUTF("bookId");
        this.bookName = reader.readUTF("bookName");
        this.level = reader.readUTF("level");
        this.owner = reader.readUTF("owner");
        this.ownerId = reader.readUTF("ownerId");
        }

}
