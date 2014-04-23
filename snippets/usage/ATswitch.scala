package HackingScala.snippets.usage {	// This will generate a new HackingScala.snippets.scala folder, if not extends App    import scala.annotation.switch;	class ATswitch {		val i = 1;				// Can be complised to a tableswitch (hashtable)		/*		 * An annotation to be applied to a match expression. 		 * If present, the compiler will verify that the match 		 * has been compiled to a tableswitch or lookupswitch, 		 * and issue an error if it instead compiles into a 		 * series of conditional expressions.
		 */		val x = (i: @switch) match {		  case 1 => "One"		  case 2 => "Two"	// If 2 is Two, warning there		  case _ => "Other"		}		/*		 * Conditions of tableswitch optimization		 * 1. The matched value must be a known integer.		 * 2. The matched expression must be simple. It cant contain any type checks, if statements, or extractors.		 * 3. The expression must also have its value available at compile time.		 * 4. There should be more than two case statements.
		 */	}}