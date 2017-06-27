
public class Catalan {

	public static int answers = 0;

	//ÇëÊµÏÖgoº¯Êý
	public static void go(Deque from, Deque to, Stack s) {
		if(from.size()==0&&s.empty()){
			answers++;
		}
		else if(from.size()==0&&!s.empty()){
			to.addLast(s.pop());
			go(from,to,s);
			s.push(to.getLast());
			to.removeLast();
		}
		else if(from.size()!=0&&s.empty()){
			s.push(from.getFirst());
			from.removeFirst();
			go(from,to,s);
			from.addFirst(s.pop());

		}
		else if(from.size()!=0&&!s.empty()){
			s.push(from.getFirst());
			from.removeFirst();
			go(from,to,s);
			from.addFirst(s.pop());

			to.addLast(s.pop());
			go(from,to,s);
			s.push(to.getLast());
			to.removeLast();

		}
	}

	public static void go2(Deque from, Deque to, Stack s) {
		if(from.size()==0&&s.empty()){
			answers++;
		}
		else if(from.size()==0&&!s.empty()){

			to.addLast(s.pop());
			go2(from,to,s);
			s.push(to.getLast());
			to.removeLast();
		}
		else if(from.size()!=0&&s.empty()){

			s.push(from.getFirst());
			from.removeFirst();
			go2(from,to,s);
			from.addFirst(s.pop());

		}
		else if(from.size()!=0&&!s.empty()){
			if(s.size()<4){
				s.push(from.getFirst());
			from.removeFirst();
			go2(from,to,s);
			from.addFirst(s.pop());
			}
			
			
			to.addLast(s.pop());
			go2(from,to,s);
			s.push(to.getLast());
			to.removeLast();

		}
	}

	public static void main(String[] args) {
		Deque from = new Deque();
		Deque to = new Deque();
		Stack s = new Stack();

		for(int i=1;i<=7;i++) {
			from.addLast(i);
		}

		go2(from, to, s);

		System.out.println(answers);


	}

}
