package cayley;

/**
 * Gremlin Query Object
 * <p>
 * Created by kai on 17/2/26.
 */
public class GremlinQuery {

    public StringBuffer content = new StringBuffer();

    public GremlinQuery(String query) {
        content.append(query);
    }

    public GremlinQuery Out(String para) {
        content.append(String.format(".Out('%s')", para));
        return this;
    }

    public GremlinQuery All() {
        content.append(".All()");
        return this;
    }

    public GremlinQuery Has(String key, String val) {
        content.append(String.format(".Has('%s','%s')", key, val));
        return this;
    }

    public GremlinQuery Follow(GremlinQuery query) {
        content.append(String.format(".Follow(%s)", query.toString()));
        return this;
    }

    /**
     * Format parameters
     * <p>
     * 1 2 3 -> '1','2','3'
     *
     * @param parameters
     * @return
     */
    private String formatParameter(String... parameters) {
        StringBuilder formattedResult = new StringBuilder();

        for (int i = 0; parameters != null && i < parameters.length; i++) {
            formattedResult.append(String.format((i == parameters.length - 1) ? "'%s'" : "'%s',", parameters[i]));
        }
        return formattedResult.toString();
    }


    @Override
    public String toString() {
        return this.content.toString();
    }
}
