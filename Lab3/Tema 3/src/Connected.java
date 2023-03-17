public class Connected {
    /**
     * arata daca graful este k conex
     * @param network graful
     * @param k numarul de conexiune
     * @return true in caz ca e k-conex sau false in caz contrar
     */
    public boolean is_k_connected(Network network, int k){
        if(k>network.getNodes().size()-1){
            return false;
        }
        if(k==1){
            return true;
        }
        for(int i=0;i<network.getNodes().size();i++){
            Network other=network;
            other.removeNode(i);
            if(!is_k_connected(other,k-1)){
                return false;
            }
        }
        return true;
    }

}
