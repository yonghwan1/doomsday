function matching(user){
  chrome.tabs.executeScript({
    code: 'document.querySelector("body").innerText'
  }, function (result) {
    // ���� �ڵ尡 ����� �Ŀ� �� �Լ��� ȣ�����ּ���. �� �� result�� ����ּ���. 
    //�� �������� body  �±� �Ʒ��� �ִ� ��� �ؽ��� �����´�. �� ����� bodyText��� ������ ��´�.
    var bodyText = result[0];
    //bodyText�� ��� �ܾ �����ϰ�, �� �ܾ��� ���ڸ� ����. �� ����� bodyNum�̶�� ������ ��´�. 
    var bodyNum = bodyText.split(' ').length;
    //bodyText���� �ڽ��� �˰� �ִ� �ܾ�(the)�� ��� �����ϴ����� �˾ƺ���. �� ����� myNum�̶�� ������ ��´�.
    var myNum = bodyText.match(new RegExp('\\b(' + user + ')\\b', 'gi')).length;
    var per = myNum / bodyNum * 100;
    per = per.toFixed(1);
    // id���� result�� �±׿� ����� �߰��Ѵ�. 
    document.querySelector('#result').innerText = myNum + '/' + bodyNum + '(' + (per) + '%)';
  });
}
 
 
//ũ�� ���丮���� ����� ���� ����������. 
chrome.storage.sync.get(function (data) {
  // #user�� ������ data�� ���� �Է����ּ���. 
  document.querySelector('#user').value = data.userWords;
 
  //�м��ؼ� �� ����� #result�� �־��ּ���. 
  matching(data.userWords);
 
});
 
//������ �������� #user �Էµ� ���� ���� �Ǿ��� '��'
document.querySelector('#user').addEventListener('change', function () {
  //������ �������� ��� �ܾ �����ϴ��� ������ּ���. 
  var user = document.querySelector('#user').value;
 
  // ũ�� ���丮���� �Է°��� �����Ѵ�. 
  chrome.storage.sync.set({
    userWords: user
  });
 
  //������ �������� ������� �ڵ带 �������ּ���. 
  matching(user);
 
});