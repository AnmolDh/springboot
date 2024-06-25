package demo.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private ObjectId id;

    @Indexed(unique=true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @DBRef
    private ArrayList<JournalEntity> journalEntities = new ArrayList<>();

}
