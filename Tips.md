IntelliJ Idea notes
psvm > Public Static void main

Pendiente > 
Page 59 @Resource to replace @Autowired.
Page 89 > Injection and ApplicationContext Nesting - No entender.

<h3>Note from book Pro Spring 6</h3>
Page 94 > Injecting Collection > Document the ways to inject a collection.
<br><br>
Note > When @Autowired is link to a List of Object it will try to inject every single Bean in the ApplicationContext 
that match the type declared in the List. <tr>
Example: If @Autowired is on List<Song> it will inject every single Bean 
with the type of Song available on ApplicationContext. if there is not Beans
on the ApplicationContext then it tries to inject List of the type of object.
Document this.

