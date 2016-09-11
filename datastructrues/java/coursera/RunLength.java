
public class RunLength {


  public void compress(Reader reader, Writer writer){
    Character c  = null;
    Character buff = null;
    int count = 0;
    c = (Character) reader.read();
    buff = c;
    while(c != null){
      if(buff == c){
        count++;
      }
      else{
        wirter.write(count);
        write.write(buff);
        buff=c;
        count = 1;
      }
      c = reader.read();
    }
  }


  private int[] count;
  public void encode(
}


