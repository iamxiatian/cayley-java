package cayley;

/**
 * Created by kai on 17/2/26.
 */
public class GraphObject {

    public GremlinQuery V(){
        return new GremlinQuery("g.V()");
    }

    public GremlinQuery V(String[] nodeIds){
        String nodes = "";
        for(int i=0; i<nodeIds.length; i++){
            nodes += String.format((i==nodeIds.length-1)? "'%s'": "'%s'," , nodeIds[i]);
        }
        return new GremlinQuery(String.format("g.V(%s)",nodes));
    }

    public GremlinQuery M(){
        return new GremlinQuery("g.Morphism()");
    }

    public GremlinQuery Vertex(){
        return V();
    }

    public GremlinQuery Vertex(String[] nodeIds){
        return V(nodeIds);
    }

    public GremlinQuery Morphism(){
        return M();
    }

    public String Emit(String context){
        return String.format("g.Emit(%s)",context);
    }
}
