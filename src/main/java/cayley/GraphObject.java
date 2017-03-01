package cayley;

/**
 * Graph Object
 *
 * @author Tian Xia
 *         Mar 01, 2017 12:40 PM
 */
public class GraphObject {
    public GremlinQuery V(){
        return new GremlinQuery("g.V()");
    }

    public GremlinQuery V(String[] nodeIds){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nodeIds.length; i++){
            sb.append(String.format((i==nodeIds.length-1)? "'%s'": "'%s'," , nodeIds[i]));
        }
        return new GremlinQuery(String.format("g.V(%s)",sb.toString()));
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
