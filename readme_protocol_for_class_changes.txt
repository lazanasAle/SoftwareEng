/*θα ονομάζετε το αρχείο commit_contents.txt*/


class: <class_name> {
    attributes:
  + attribute1  /* το "+" στην αρχή δηλώνει ότι προστέθηκε attribute αν αφαιρέσατε κάποιο βάζετε "-". Στην θέση του "attributeX" (πχ εκεί που γράφει "attribute1") γράφετε όλο το signature του attribute πχ αμα έιναι ένα απλό Public/Private/Protected attribute γράφετε public/private/protected                      (ό,τι ταιριάζει) <data_type> <attribute_name>  
                  αν το attribute είναι static το γραφετε ρητά δίπλα από το Visibility (public/private/protected) το ίδιο ισχύει και για final δηλαδή: μια ολοκληρωμένη αλλαγή σε attribute θα εκφράζεται με τον εξής αναλυτικό τρόπο (EBNF Syntax):
                  <προσθήκη_attribute> ::= + [public|protected|private] [static] [final] <data_type> <attribute_name> 
                  <αφαίρεση_attribute> ::= - [public|protected|private] [static] [final] <data_type> <attribute_name> */
  + attribute2
  + attribute3
  methods:
  + method1  /* το "+" στην αρχή δηλώνει ότι προστέθηκε method αν αφαιρέσατε κάποια βάζετε "-". Στην θέση του "methodX" (πχ εκεί που γράφει "method1") γράφετε όλο το signature της μεθόδου  δηλαδή: μια ολοκληρωμένη αλλαγή (προσθήκη/αφαίρεση) σε μέθοδο θα εκφράζεται με τον εξής αναλυτικό τρόπο (EBNF Syntax):
                  <προσθήκη_μεθόδου> ::= + [public|protected|private] [static] <return_data_type> <method_name> (<full_arg_list>) [throws xyzException]
                  <αφαίρεση_μεθόδου> ::= - [public|protected|private] [static] <return_data_type> <method_name> (<full_arg_list>) [throws xyzException] */
  + method2
  + method3


  /* Σχόλια σε όποια προσθήκη κάνετε γράφετε σε τέτοια sections ανάμεσα σε /*<Σχόλια>*/  όπως γράφω εγώ επεξηγήσεις */
  
  }
