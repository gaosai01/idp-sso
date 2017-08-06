package com.gaosai.idp.common;

public class Result {
		
	private int code;
	private String msg;
	private Object data;
	private Result(){}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static Result success( Object data ){
		Result ans = new Result();
		ans.setCode( Config.CODE.SUCCESS );
		ans.setMsg( "成功" );
		ans.setData( data );
		return ans;
	}
	public static Result success( String msg ){
		Result ans = new Result();
		ans.setCode( Config.CODE.SUCCESS );
		ans.setMsg( msg );
		ans.setData( null );
		return ans;
	}

	public static Result success( Object data, String msg ){
		Result ans = new Result();
		ans.setCode( Config.CODE.SUCCESS );
		ans.setMsg( msg );
		ans.setData( data );
		return ans;
	}

	public static Result error(){
		Result ans = new Result();
		ans.setCode( Config.CODE.ERROR );
		ans.setMsg( "失败" );
		ans.setData(null);
		return ans;
	}
	public static Result error( String msg ){
		Result ans = new Result();
		ans.setCode( Config.CODE.ERROR );
		ans.setMsg( msg );
		ans.setData(null);
		return ans;
	}
	public static Result error( String msg, Object data ){
		Result ans = new Result();
		ans.setCode( Config.CODE.ERROR );
		ans.setMsg( msg );
		ans.setData(data);
		return ans;
	}
	private static Result error( int code, String msg, Object data ){
		Result ans = new Result();
		ans.setCode( code );
		ans.setMsg( msg );
		ans.setData(data);
		return ans;
	}

	

}
