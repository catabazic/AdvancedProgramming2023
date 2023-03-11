public abstract class Algorithm {
    protected Location[] locations;
    protected Road[] roads;
    protected Solution solutie=new Solution();
    public abstract Solution solve();

}
