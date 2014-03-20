package unionFind;


/**
 * Interface for Union Find Algo
 **/


public interface UF{

  public void union(int first, int next);

  public boolean connected(int first, int next);

}
