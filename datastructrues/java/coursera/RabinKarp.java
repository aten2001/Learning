
public class RabinKarp {

  private final int hash;
  private final int Q; // long prime
  private final int R; // radix = 256
  private final int M; // length of patt
  private long RM;


  public RabinKarp(String patt){
    this.Q = 997;
    this.R = 256;
    this.M = patt.length();
    this.hash = hash(patt,M);
    this.RM = 1;
    for(int i=0; i<patt.length(); i++){
      RM = (R * RM) % Q;
    }
  }

  public void substrings(String data){
     int N = data.length();
     int dataHash = hash(data,M);
     if(hash == datahash){
      System.out.println(data.substring(0,M);
      return;
     }
     for(int i=M; i<N; i++){
       datahash = (datahash + Q - RM*data.charAt(i-M) %Q) %Q;
       datahash = (datahash * R + data.charAt(i)) % Q;
       if(hash == datahash) return i - M + 1;
     }
  }

  private long hash(String key, int M){
    long h = 0;
    for(int j=0; j<M; j++){
      h = (R * h + key.charAt(j)) % Q;
    }
    return h;
  }

}
