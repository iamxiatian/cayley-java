package cayley;

/**
 * Created by kai on 17/2/26.
 * Gremlin语言查询对象
 */
public class GremlinQuery {

    public StringBuffer text = new StringBuffer();

    public GremlinQuery(String query){
        text.append(query);
    }

    public GremlinQuery Out(String para){
        text.append(String.format(".Out('%s')", para));
        return this;
    }

    public GremlinQuery All(){
        text.append(".All()");
        return this;
    }

    public GremlinQuery Has(String key, String val){
        text.append(String.format(".Has('%s','%s')", key, val));
        return this;
    }

    public GremlinQuery Follow(GremlinQuery query){
        text.append(String.format(".Follow(%s)",query.toString()));
        return this;
    }

    //TODO 其他命令

    /**
     * 讲多个参数格式化
     * 1 2 3 -> '1','2','3'
     * @param paramers
     * @return
     */
    private String formatParamer(String ... paramers){
        String fomart = "";
        if(null != paramers){
            for(int i=0; i<paramers.length; i++){
                fomart += String.format((i==paramers.length-1)? "'%s'": "'%s',", paramers[i]);
            }
        }
        return fomart;
    }


    @Override
    public String toString() {
        return this.text.toString();
    }
}
