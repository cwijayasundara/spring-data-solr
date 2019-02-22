package com.cham.solrsample.populator;

import com.cham.solrsample.domain.HazelcastBook;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class DataPopulatorMain {

    private final static String mapName="book-map";

    public static void main(String args[]){

        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        serializationConfig.addPortableFactoryClass(1, "com.cham.solrsample.domain.PortableFactoryImpl");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        // create the IMap
        IMap<String, HazelcastBook> serverBookMap = client.getMap(mapName);

        serverBookMap.clear();

        HazelcastBook book1 = new HazelcastBook("004","Spider Man","L1", "Chaminda", "1111");
        HazelcastBook book2 = new HazelcastBook("005","Sand Man","L1", "Dinka", "2222");
        HazelcastBook book3 = new HazelcastBook("006","Venom","L1", "Tom", "3333");
        HazelcastBook book4 = new HazelcastBook("007","Wonder woman","L1", "Sally", "8888");
        HazelcastBook book5 = new HazelcastBook("008","Super Man","L1", "Silly", "6666");

        serverBookMap.set("004", book1);
        serverBookMap.set("005", book2);
        serverBookMap.set("006", book3);
        serverBookMap.set("007", book4);
        serverBookMap.set("008", book5);

    }

}
