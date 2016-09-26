package controller;


public class IdentificationException extends Exception {
		private String login,mdp;
		public IdentificationException(String login,String mdp) {
			// TODO Auto-generated constructor stub
			this.login=login;
			this.mdp=mdp;
		}
		@Override
		public String getMessage() {
		// TODO Auto-generated method stub
			return "Error identifying the user. Wrong Login Or Password [ Login :"+ this.login+" ; pass  :"+this.mdp+" ].";
		}
}
