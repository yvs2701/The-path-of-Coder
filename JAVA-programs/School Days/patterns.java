class patterns
{ public static void main(String ar[])
{ char ch, ch1;
  for(ch='e';ch>='a';ch--)
{ for(ch1='e';ch1>=ch;ch1--)
  System.out.print(" ");
  for(ch1='a';ch1<=ch;ch1++)
  System.out.print(ch1);
  System.out.println();
}
int n=5;
for (int i = 1; i <=n; i++) {
  for (int j = 1; j <=n-i; j++) {
    System.out.print(" ");
  }
  for (int j = 1; j <=i; j++) {
    System.out.print(j);
  }
  System.out.println();
}
for (int i = 1; i <=n; i++) {
  for (int j = 1; j <=n-i; j++) {
    System.out.print(" ");
  }
  for (int j = 1; j <=i; j++) {
    System.out.print(j+" ");
  }
  System.out.println();
}
}}