package org.learning.indexing;

import java.lang.reflect.Field;


public class Indexer 
{
   private final IIndexDocStore indexDocStore; //store indexToDocId
   private final IDocIndexStore docIndexStore; // store docToIndexId
   private final IDocStore docStore; //store docId to DocData
   private final IIndexStore indexStore; //store indexId to IndexLabel
   private final IIdCounter idCounter; //incrementIndex
   
   public Indexer(IIndexDocStore indexDocStore,
		   IDocIndexStore docIndexStore,
		   IIndexStore indexStore,
		   IDocStore docStore,
		   IIdCounter idCounter ){
	   this.indexStore = indexStore;
	   this.docStore = docStore;
	   this.indexDocStore = indexDocStore;
	   this.docIndexStore = docIndexStore;
	   this.idCounter = idCounter;
   }
   
   
   public <T> void indexDoc(T obj, String... indexFields) throws IndexException{
	  try{
		  //create docId and store Doc
		  long docId = idCounter.incrementAndGetDocId();
		  byte[] docVal = SerDe.serialize(obj);
		  Doc doc = new Doc(docId, docVal);
		  docStore.create(doc);
		  // fetch Index fields and builder indexDoc and docIndex map
		  Class<T> clazz  = (Class<T>) obj.getClass();
		   for(String field: indexFields){
			   Field fld = clazz.getDeclaredField(field);
			   fld.setAccessible(true);
			   Object val = fld.get(obj);
			   byte[] byteVal = SerDe.serialize(val);
			   long indexId = idCounter.incrementAndGetIndexId();
			   Index index = new Index(indexId, byteVal);
			   indexStore.create(index);
			   indexDocStore.update(indexId,docId); //adds to sortedSet
			   docIndexStore.update(docId,indexId); //adds to sortedSet
		   }
	  }catch(Exception e){
		  throw new IndexException(e);
	  }
   }
   
   public void searchAndK();
   
}
