package compiler;

//onTrue  =>  spring naar knooppunt 1
//onFalse => spring naar knooppunt 2

public class ConditionalJump extends Node {

	boolean state;
	
	public void setState(boolean state){	
		this.state = state;
	}
	
	public boolean getState(){
		return state;
	}
}
