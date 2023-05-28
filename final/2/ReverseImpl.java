import ReverseModule.ReversePOA; 
import java.lang.String; 

class ReverseImpl extends ReversePOA
{
	ReverseImpl() { } // no super call needed
	
	public String reverse_string(String name)
	{
		StringBuffer str = new StringBuffer(name); 
		str.reverse(); 
		return (("server Send"+str));
	}
}
