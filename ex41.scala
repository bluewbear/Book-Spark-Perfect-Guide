sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = {
    this match {
      case None    => None
      case Some(a) => Some(f(a))
    }
  }

  def flatMap[B](f: A => Option[B]): Option[B] = {
    this match {
      case None    => None
      case Some(a) => if (f(a) == None) None else f(a)
    }
  }

  def getOrElse[B >: A](default: => B): B = {
    this match {
      case None    => default
      case Some(a) => a
    }
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = {
    this match {
      case None    => ob
      case Some(a) => Some(a)
    }
  }

  def filter(f: A => Boolean): Option[A] = {
    this match {
      case None              => None
      case Some(a) => if ((f(a)) == None) None else Some(a)
    }
  }
}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

case class Employee(name: String, department: String)
def lookupByName(name: String): Option[Employee] = {
  if ("Joe" == name) Some(new Employee("Joe", "IT"))
  else None
}

val joeDepartment: Option[String] = lookupByName("Joe").map(_.department)

print(joeDepartment)

def mean(xs: Seq[Doble]): Option[Double] =
  if (xs.isEmpty) None
  else Some(xs.sum / xs.length)


