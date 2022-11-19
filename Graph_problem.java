import java.util.LinkedList;
import java.util.Scanner;
public class Graph_problem {
        int distsum,path;
     static class Edge{
        String src,dest;
        int weight;
        Edge(String src,String dest,int weight)
        {
            this.src=src;
            this.dest=dest;
            this.weight=weight;
        }
    }
    static class Graph{
        int v,distsum=0,path=0;
        LinkedList<Edge> adj[];
        Graph(int v)
        {
            this.v=v;
            adj=new LinkedList[v];
            for(int i=0;i<v;i++)
            {
                adj[i]=new LinkedList<>();
            }
        }
        public void addEdge(String src,String dest,int weight)
        {
            Edge ed1=new Edge(src,dest,weight);
            Edge ed2=new Edge(dest,src,weight);
            int ind1=(int)(src.charAt(0))-'A';
            int ind2=(int)(dest.charAt(0))-'A';
            adj[ind1].addFirst(ed1);
            adj[ind2].addFirst(ed2);
        }
        public double Distance(String src,String dest){
            int vis[]=new int[v];
            for(int i=0;i<v;i++)
                vis[i]=0;
            vis[src.charAt(0)-'A']=1;
            dfs(src,dest,vis,0);
            return (double)distsum/(double)path;
        }
        public void dfs(String src,String dest,int vis[],int distance){
            if (src.equals(dest)){
                path=path+1;
                distsum+=distance;
                return;
            }
            int intSrc=src.charAt(0)-'A';
            for(Graph_problem.Edge node:adj[intSrc]){
                String temp=node.dest;
                int intDest=(int)temp.charAt(0)-'A';
                if (vis[intDest]==0){
                    vis[intDest]=1;
                    dfs(temp,dest,vis,distance+node.weight);
                    vis[intDest]=0;
                }
            }
            return;

        }
        public static void main(String[] args)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter source");
            String source=sc.next();
            System.out.println("Enter destination");
            String destination= sc.next();
            Graph gr=new Graph(5);
            gr.addEdge("A","B",12);
            gr.addEdge("A","C",13);
            gr.addEdge("A","D",11);
            gr.addEdge("A","E",8);
            gr.addEdge("B","C",3);
            gr.addEdge("C","E",4);
            gr.addEdge("D","E",7);
            System.out.println(gr.Distance(source, destination));
        }
    }
}