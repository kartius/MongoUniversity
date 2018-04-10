package course;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class Exam_1 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("school");
        MongoCollection<Document> collection = database.getCollection("students");
//        Bson filter = eq("_id", 137);
//        FindIterable<Document> id = collection.find(filter);
        List<Document> all = collection.find().into(new ArrayList<Document>());
        List<Document> newScores = null;
        for (Document document : all) {
            System.out.println(document.get("name"));
            System.out.println(document.get("_id"));
            List<Document> scores = (List<Document>) document.get("scores");
            newScores = new ArrayList<Document>();
            Document scoreDocument = null;
            List<Double> temp = new ArrayList<Double>();
            for (Document score : scores) {
                if (!score.get("type").equals("homework")) {
                    scoreDocument = new Document();
                    scoreDocument.append("type", score.get("type"));
                    scoreDocument.append("score", score.get("score"));
                    newScores.add(scoreDocument);
                } else {
                    temp.add((Double) score.get("score"));
                    if (temp.size() > 1) {
                        scoreDocument = new Document();
                        scoreDocument.append("type", "homework");
                        if (temp.get(0) > temp.get(1)) {
                            scoreDocument.append("score", temp.get(0));
                        } else {
                            scoreDocument.append("score", temp.get(1));
                        }
                        temp.clear();
                        newScores.add(scoreDocument);
                    }
                }
            }
//            newScores.add(scoreDocument);
            collection.updateOne(new Document("name", document.get("name")), new Document("$set", new Document("scores", newScores)));
            System.out.println(newScores.size());


        }
        for (Document newScore : newScores) {
            System.out.println(newScore);
            Helpers.printJson(newScore);
        }

    }
}
