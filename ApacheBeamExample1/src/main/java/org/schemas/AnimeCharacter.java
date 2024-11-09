package org.schemas;

import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaCreate;
import org.apache.beam.sdk.schemas.JavaBeanSchema;

@DefaultSchema(JavaBeanSchema.class) //This annotation tells Beam to infer schemas from a specific type
//Takes a SchemaProvider as an argument
public class AnimeCharacter {

    //ID for the character
    public int id;
    //Name of the character
    public String name;
    //Anime show
    public String show;

    //SchemaCreate annotation tells Beam that this constructor can be used to create instances of the POJO,
    //assuming that constructor parameters have the same names as the field names. It can also be used to
    //annotate static factory methods on the class, allowing the constructor to remain private.
    //If there is no SchemaCreate all fields must be non-final and the class must have a zero-argument constructor
    @SchemaCreate
    public AnimeCharacter(int id, String name, String show){
        this.id = id;
        this.name = name;
        this.show = show;
    }

    public String getName(){
        return this.name;
    }

    public String getShow() {
        return this.show;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return this.id + "," + this.name + "," + this.show;
    }

}
