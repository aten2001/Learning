
public class IQOneThree {

  static class Index {
    Character ch;
    int i;
    int jump;
    public Index(int i, int jump , char ch){
      this.i=i;
      this.jump=jump;
      this.ch=ch;
    }

    @Override
    public int hashCode(){
      return ch.hashCode();
    }

    @Override
    public boolean equals(Object obj){
      if(obj == null) return false;
      if(obj instanceof Index){
        Index ind = (Index) obj;
        return ind.ch.equals(this.ch);
      }
      else return false;
    }

  }

  public String longestSubString(String data, int N){
    Character buff = null;
    List<Index> indxs = new ArrayList<Index>();
    int j=0;
    for(int i=0; i<data.length; i++){
      Character ch = data.charAt(i);
      if(ch.equals(buff)) {
        Index ind = indxs.get(j-1);
        ind.jump++;
        indx.add(j-1,ind);
      }else{
        buff=ch;
        indxs.add(new Index(i,1,ch));
        j++;
      }
    }

    Map<Character,Boolean> map = new HashMap<Character,Boolean>();
    for(int i=0; i<indxs.size(); i++){
      Index ind = indxs.get(i);
      if(map.size() == N && !map.containsKey(ind)){
        if(

      }
      map.put(ind,true);
    }

  }
}





