# 커맨드 패턴
어떤 작업을 요청하는 행위 자체를 캡슐화 한 것

커맨트 패턴 적용 전
```
작업 요청자 ->  작업 수행자
```
```
작업 요청자     ->  A 작업 수행자  
		        ->  B 작업 수행자  
		        -> . . . .  
```

커맨드 패턴 적용 후 
``` 
작업 요청자 -> 작업   
			 L   A 작업 -> A 작업 수행자  
			 L   B 작업 -> B 작업 수행자  
							    
```
작업이라는 행위 자체를 캡슐화하여 작업을 요청하는 인보커와 실제 작업을 수행하는 리시버 사이에 분리가 가능


## 커맨드 패턴 구성요소

커맨드 객체  
-	작업에 대한 행위 명시 - execute()  
-	작업을 수행하는 대상(리시버)을 알고 있음  

인보커 객체
-	커멘드 객체 컨테이너의 역할

클라이언트 객체
-	커맨드 객체를 생성하는 주체
-	인보커 객체의 setCommand() 를 통해 생성한 커멘드 객체를 인보커에 추가

리시버 객체
-	실제 작업을 수행하는 객체


### 객체를 매개변수화 한다는게 어떤 뜻일까?
커맨드 패턴을 사용하면 요청 내역을 객체로 캡슐화 해서 객체를 서로다른 요청 내역에 따라 매개변수화 할 수 있습니다. 이러면 요청을 큐에 저장하거나 로그로 기록하거나 작업 취소 기능을 사용 할 수 있습니다.




코드 예시 ) - 텍스트 에디터

작업 요청자 -> 작업
			 L   'hello' 라고 쓰기 작업 -> 텍스트 작업 수행자
			 L   'world' 라고 쓰기 작업 -> 텍스트 작업 수행자

```typescript

// Receiver: 텍스트 편집기 클래스
class TextEditor {
  private text: string = "";

  addText(text: string) {
    this.text += text;
    console.log("텍스트 추가:", text);
  }

  getText(): string {
    return this.text;
  }
}

// Command: 명령 인터페이스
interface Command {
  execute(): void;
}

// ConcreteCommand: 텍스트 추가 기능을 수행하는 명령 클래스
class AddTextCommand implements Command {
  private editor: TextEditor;
  private textToAdd: string;

  constructor(editor: TextEditor, textToAdd: string) {
    this.editor = editor;
    this.textToAdd = textToAdd;
  }

  execute(): void {
    this.editor.addText(this.textToAdd);
  }
}

// Invoker: 명령을 실행하는 객체
class Invoker {
  private command: Command | null = null;

  setCommand(command: Command) {
    this.command = command;
  }

  executeCommand() {
    if (this.command) {
      this.command.execute();
    } else {
      console.log("명령이 설정되지 않았습니다.");
    }
  }
}

// 클라이언트 코드
function main() {
  const editor = new TextEditor();
  const invoker = new Invoker();

  // 명령 생성 및 설정
  const command1 = new AddTextCommand(editor, "Hello, ");
  const command2 = new AddTextCommand(editor, "World!");

  invoker.setCommand(command1);
  invoker.executeCommand(); // "텍스트 추가: Hello, "

  invoker.setCommand(command2);
  invoker.executeCommand(); // "텍스트 추가: World!"

  console.log("최종 결과:", editor.getText()); // "Hello, World!"
}

main();

```


### 여기에서 파일 복사 하는 명령을 추가한다면? 

작업 요청자 -> 작업
			 L   'hello' 라고 쓰기 작업 -> 텍스트 작업 수행자
			 L   'world' 라고 쓰기 작업 -> 텍스트 작업 수행자
			 L   현재 에디터 내용 저장 작업  -> 텍스트 작업 수행자 
			 L   (에디터 작업이랑 아예 상관없는) 파일 복사 작업 -> 파일 작업 수행자
			 L  ...

```typescript

import * as fs from 'fs';

// Receiver: 텍스트 편집기 클래스
class TextEditor {
  private text: string = "";

  addText(text: string) {
    this.text += text;
    console.log("텍스트 추가:", text);
  }

  saveToFile(filePath: string) {
    fs.writeFileSync(filePath, this.text);
    console.log("텍스트 저장 완료:", filePath);
  }

  getText(): string {
    return this.text;
  }
}

// Command: 명령 인터페이스
interface Command {
  execute(): void;
}

// ConcreteCommand: 텍스트 추가 기능을 수행하는 명령 클래스
class AddTextCommand implements Command {
  private editor: TextEditor;
  private textToAdd: string;

  constructor(editor: TextEditor, textToAdd: string) {
    this.editor = editor;
    this.textToAdd = textToAdd;
  }

  execute(): void {
    this.editor.addText(this.textToAdd);
  }
}

// ConcreteCommand: 파일 복사 기능을 수행하는 명령 클래스
class CopyFileCommand implements Command {
  private sourceFilePath: string;
  private destinationFilePath: string;

  constructor(sourceFilePath: string, destinationFilePath: string) {
    this.sourceFilePath = sourceFilePath;
    this.destinationFilePath = destinationFilePath;
  }

  execute(): void {
    // 파일 복사 기능을 파일 시스템을 이용하여 구현
    fs.copyFileSync(this.sourceFilePath, this.destinationFilePath);
    console.log(`파일 복사 완료: ${this.sourceFilePath} -> ${this.destinationFilePath}`);
  }
}

// ConcreteCommand: 파일 저장 기능을 수행하는 명령 클래스
class SaveToFileCommand implements Command {
  private editor: TextEditor;
  private filePath: string;

  constructor(editor: TextEditor, filePath: string) {
    this.editor = editor;
    this.filePath = filePath;
  }

  execute(): void {
    this.editor.saveToFile(this.filePath);
  }
}

// Invoker: 명령을 실행하는 객체
class Invoker {
  private command: Command | null = null;

  setCommand(command: Command) {
    this.command = command;
  }

  executeCommand() {
    if (this.command) {
      this.command.execute();
    } else {
      console.log("명령이 설정되지 않았습니다.");
    }
  }
}

// 클라이언트 코드
function main() {
  const editor = new TextEditor();
  const invoker = new Invoker();

  // 텍스트 추가 명령 생성 및 실행
  const command1 = new AddTextCommand(editor, "Hello, ");
  const command2 = new AddTextCommand(editor, "World!");
  invoker.setCommand(command1);
  invoker.executeCommand();
  invoker.setCommand(command2);
  invoker.executeCommand();

  // 파일 저장 명령 생성 및 실행
  const saveFilePath = 'savedText.txt';
  const command4 = new SaveToFileCommand(editor, saveFilePath);
  invoker.setCommand(command4);
  invoker.executeCommand();

  // 파일 복사 명령 생성 및 실행
  const sourceFile = 'savedText.txt';
  const destinationFile = 'copyedText.txt';
  const command3 = new CopyFileCommand(sourceFile, destinationFile);
  invoker.setCommand(command3);
  invoker.executeCommand();

  console.log("최종 결과:", editor.getText());
}

main();


```



### 명령을 추가하는 행위 말고 이전 명령을 취소하는 행위도 추상화 해보자

Command 에 execute 행위 와 비슷하게 undo 메서드도 추가
인보커에는 Command를 실행하는 것과 반대로 되돌리는 코드 추가
인보커에서 여러번 연속으로 되돌릴 수도 있으니까 실행 내역을 저장할 필요 생김

```typescript
// Command: 명령 인터페이스
interface Command {
  execute(): void;
  undo(): void;
}
```

```typescript
// ConcreteCommand: 텍스트 추가 기능을 수행하는 명령 클래스
class AddTextCommand implements Command {
  // ... (생략)

  undo(): void {
    // 추가한 텍스트를 제거하여 이전 상태로 되돌림
    this.editor.addText(this.textToAdd);
    console.log("텍스트 추가 취소:", this.textToAdd);
  }
}

// ConcreteCommand: 파일 복사 기능을 수행하는 명령 클래스
class CopyFileCommand implements Command {
  // ... (생략)

  undo(): void {
    // 복사한 파일을 삭제하여 이전 상태로 되돌림
    fs.unlinkSync(this.destinationFilePath);
    console.log(`파일 복사 취소: ${this.destinationFilePath}`);
  }
}

// ConcreteCommand: 파일 저장 기능을 수행하는 명령 클래스
class SaveToFileCommand implements Command {
  // ... (생략)

  undo(): void {
    // 파일 저장을 취소하여 이전 상태로 되돌림
    console.log("파일 저장 취소:", this.filePath);
  }
}

```

```typescript
// Invoker: 명령을 실행하고 취소하는 객체
class Invoker {
  private command: Command | null = null;
  private history: Command[] = [];

  setCommand(command: Command) {
    this.command = command;
  }

  executeCommand() {
    if (this.command) {
      this.command.execute();
      this.history.push(this.command);
    } else {
      console.log("명령이 설정되지 않았습니다.");
    }
  }

  undoCommand() {
    const lastCommand = this.history.pop();
    if (lastCommand) {
      lastCommand.undo();
    } else {
      console.log("실행 취소할 명령이 없습니다.");
    }
  }
}

```

```typescript
// 클라이언트 코드
function main() {
  const editor = new TextEditor();
  const invoker = new Invoker();

  // 텍스트 추가 명령 생성
  const command1 = new AddTextCommand(editor, "Hello, ");
  const command2 = new AddTextCommand(editor, "World!");

  // 파일 복사 명령 생성
  const sourceFile = 'source.txt';
  const destinationFile = 'destination.txt';
  const command3 = new CopyFileCommand(sourceFile, destinationFile);

  // 파일 저장 명령 생성
  const saveFilePath = 'savedText.txt';
  const command4 = new SaveToFileCommand(editor, saveFilePath);

  // 텍스트 추가 명령 실행
  invoker.setCommand(command1);
  invoker.executeCommand();
  invoker.setCommand(command2);
  invoker.executeCommand();

  // 파일 복사 명령 실행
  invoker.setCommand(command3);
  invoker.executeCommand();

  // 파일 저장 명령 실행
  invoker.setCommand(command4);
  invoker.executeCommand();

  // 실행 취소
  invoker.undoCommand(); // 파일 저장 취소: savedText.txt
  invoker.undoCommand(); // 파일 복사 취소: destination.txt
  invoker.undoCommand(); // 텍스트 추가 취소: World!
  invoker.undoCommand(); // 텍스트 추가 취소: Hello,

  console.log("최종 결과:", editor.getText()); // 빈 문자열 출력
}

main();

```


# 데이터베이스 트랜잭션과 커맨드 패턴의 유사성
1. 작업 행위자체를 캡슐화:

커맨드 패턴에서 명령 객체는 특정 동작을 캡슐화하여 실행을 요청하는 객체에 전달합니다. 마찬가지로 데이터베이스 트랜잭션에서도 하나의 논리적인 단위로 데이터베이스 연산을 캡슐화하여 실행을 요청합니다.

2. 실행 취소

커맨드 패턴에서는 명령 객체가 undo() 메서드를 통해 실행 취소 기능을 제공할 수 있습니다. 데이터베이스 트랜잭션에서도 롤백(Rollback)을 통해 트랜잭션을 취소하고 이전 상태로 되돌릴 수 있습니다.

3. 일관성 유지

커맨드 패턴은 명령 객체를 사용하여 여러 동작을 순차적 또는 동시에 실행하고, 데이터베이스 트랜잭션도 여러 개의 연산을 하나의 논리적인 단위로 묶어서 일관성을 유지합니다.

데이터베이스 트랜잭션과 커맨드 패턴은 서로 다른 문제를 해결하기 위해 설계되었으며, 근본적으로 목적과 구조가 다릅니다. 하지만 두 패턴은 비슷한 개념들을 공유하고 있기 때문에, 데이터베이스 트랜잭션을 구현하는 데에도 커맨드 패턴을 활용하여 트랜잭션의 세부적인 로직을 추상화하고 관리가능


### Q3. 여러 명령을 한번에 실행하려면 어떻게 해야할까?
=> 요청을 큐에 저장해보자!

```typescript
// CompositeCommand: 복합 명령 클래스
class CompositeCommand implements Command {
  private commands: Command[] = []; // Command를 저장 할 큐

  addCommand(command: Command) {
    this.commands.push(command);
  }

  execute(): void {
    for (const command of this.commands) {
      command.execute();
    }
  }
}

// 클라이언트 코드
function main() {
  const editor = new TextEditor();
  const invoker = new Invoker();

  // 텍스트 추가 명령 생성
  const command1 = new AddTextCommand(editor, "Hello, ");
  const command2 = new AddTextCommand(editor, "World!");

  // 파일 복사 명령 생성
  const sourceFile = 'source.txt';
  const destinationFile = 'destination.txt';
  const command3 = new CopyFileCommand(sourceFile, destinationFile);

  // 파일 저장 명령 생성
  const saveFilePath = 'savedText.txt';
  const command4 = new SaveToFileCommand(editor, saveFilePath);

  // 복합 명령 생성 및 추가
  const compositeCommand = new CompositeCommand();
  compositeCommand.addCommand(command1);
  compositeCommand.addCommand(command2);
  compositeCommand.addCommand(command3);
  compositeCommand.addCommand(command4);

  // 복합 명령 실행
  invoker.setCommand(compositeCommand);
  invoker.executeCommand();

  console.log("최종 결과:", editor.getText());
}

main();
```

execute() 함수 내에서 각 Command의 실행 순서를 제어 가능
=> 큐 : FIFO, 스택: LIFO, 혹은 동시성 처리
=> 스케줄러, 작업 큐, 스레드 풀에 활용


# 정리
### 커맨드 패턴은 작업을 요청하는 객체와 실제 작업을 수행하는 객체간 분리를 위한 것
작업이라는 행위 자체를 캡슐화하여 작업을 요청하는 인보커와 실제 작업을 수행하는 리시버 사이에 분리가 가능

